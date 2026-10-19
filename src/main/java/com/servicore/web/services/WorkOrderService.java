package com.servicore.web.services;

import java.math.BigDecimal;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicore.web.dtos.request.WorkOrderRequestDTO;
import com.servicore.web.dtos.request.WorkOrderTechnicinaRequestDTO;
import com.servicore.web.dtos.response.WorkOrderResponseDTO;
import com.servicore.web.enums.TechnicianRole;
import com.servicore.web.exceptions.ResourceNotFoundException;
import com.servicore.web.models.Company;
import com.servicore.web.models.Customer;
import com.servicore.web.models.Priority;
import com.servicore.web.models.ServiceModel;
import com.servicore.web.models.Technician;
import com.servicore.web.models.WorkOrder;
import com.servicore.web.models.WorkOrderStatus;
import com.servicore.web.models.WorkOrderTechnician;
import com.servicore.web.repositories.CompanyRepository;
import com.servicore.web.repositories.CustomerRepository;
import com.servicore.web.repositories.PriorityRepository;
import com.servicore.web.repositories.ServiceRepository;
import com.servicore.web.repositories.TechnicianRepository;
import com.servicore.web.repositories.WorkOrderRepository;
import com.servicore.web.repositories.WorkOrderStatusRepository;
import com.servicore.web.repositories.WorkOrderTechnicinaRepository;

@Service 
public class WorkOrderService {
    
    private final WorkOrderRepository workOrderRepository;
    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;
    private final ServiceRepository serviceRepository;
    private final PriorityRepository priorityRepository;
    private final WorkOrderStatusRepository workOrderStatusRepository;
    private final TechnicianRepository technicianRepository;
    private final WorkOrderTechnicinaRepository workOrderTechnicianRepository;

    public WorkOrderService(
        WorkOrderRepository workOrderRepository,
        CompanyRepository companyRepository,
        CustomerRepository customerRepository,
        ServiceRepository serviceRepository,
        PriorityRepository priorityRepository,
        WorkOrderStatusRepository workOrderStatusRepository,
        TechnicianRepository technicianRepository,
        WorkOrderTechnicinaRepository workOrderTechnicianRepository
    ) {
        this.workOrderRepository = workOrderRepository;
        this.companyRepository = companyRepository;
        this.customerRepository = customerRepository;
        this.serviceRepository = serviceRepository;
        this.priorityRepository = priorityRepository;
        this.workOrderStatusRepository = workOrderStatusRepository;
        this.technicianRepository = technicianRepository;
        this.workOrderTechnicianRepository = workOrderTechnicianRepository;
    }

    @Transactional(readOnly = true)
    public List<WorkOrderResponseDTO> getWorkOrdersByCompanyId(Long companyId) {

        return workOrderRepository
            .findByCompany_Id(companyId)
            .stream()
            .map(this::toResponseDTO)
            .toList();

    }

    @Transactional
    public WorkOrderResponseDTO createWorkOrder(WorkOrderRequestDTO dto) {

        Company company = companyRepository
            .findById(dto.getCompanyId())
            .orElseThrow(() ->
                new ResourceNotFoundException("Compania no encontrada.")
            );

        Customer customer = customerRepository
            .findById(dto.getCustomerId())
            .orElseThrow(() ->
                new ResourceNotFoundException("Cliente no encontrado.")
            );

        ServiceModel service = serviceRepository
            .findById(dto.getServiceId())
            .orElseThrow(() ->
                new ResourceNotFoundException("Servicio no encontrado.")
            );

        Priority priority = priorityRepository
            .findById(dto.getPriorityId())
            .orElseThrow(() ->
                new ResourceNotFoundException("Prioridad no encontrada.")
            );  

        WorkOrderStatus workOrderStatus = workOrderStatusRepository
            .findById(dto.getWorkOrderStatusId())
            .orElseThrow(() -> 
                new ResourceNotFoundException("Estado de orden no encontrado.")
            );  

        String orderCode = generateWorkOrderCode(dto.getCompanyId());
        BigDecimal estimatedPrice = service.getBasePrice();
        BigDecimal finalPrice = estimatedPrice;

        WorkOrder workOrder = new WorkOrder(
            company, 
            orderCode, 
            customer, 
            service, 
            dto.getProgrammedDate(), 
            dto.getServiceAddress(), 
            dto.getProblemDescription(), 
            priority, 
            workOrderStatus, 
            estimatedPrice,
            finalPrice
        );

        WorkOrder saved = workOrderRepository.save(workOrder);

        assignTechnicians(workOrder, company, dto.getTechnicians());
        
        return toResponseDTO(saved);
    }


    private void assignTechnicians(
            WorkOrder workOrder,
            Company company,
            List<WorkOrderTechnicinaRequestDTO> techniciansDTO) {

        Set<Long> assignedTechnicianIds = new HashSet<>();
        List<WorkOrderTechnician> assignments = new java.util.ArrayList<>();

        int leadTechniciansCount = 0;

        for (WorkOrderTechnicinaRequestDTO technicianDTO : techniciansDTO) {

            Long technicianId = technicianDTO.getTechnicianId();

            if (!assignedTechnicianIds.add(technicianId)) {
                throw new IllegalArgumentException(
                    "Un técnico no puede asignarse dos veces a la misma orden."
                );
            }

            Technician technician = technicianRepository
                .findById(technicianId)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Técnico no encontrado: " + technicianId
                    )
                );

            if (!technician.getUser().getCompany().getId()
                    .equals(company.getId())) {
                throw new IllegalArgumentException(
                    "El técnico " + technicianId
                    + " no pertenece a la empresa de la orden."
                );
            }

            if (technicianDTO.getTechnicianRole()
                    == TechnicianRole.LEAD_TECHNICIAN) {
                leadTechniciansCount++;
            }

            assignments.add(
                new WorkOrderTechnician(
                    technician,
                    workOrder,
                    technicianDTO.getTechnicianRole()
                )
            );
        }

        if (leadTechniciansCount != 1) {
            throw new IllegalArgumentException(
                "La orden debe tener exactamente un técnico principal."
            );
        }

        workOrderTechnicianRepository.saveAll(assignments);
    }


    public String generateWorkOrderCode(Long companyId) {

        int year = Year.now().getValue();
        String prefix = "SRV-" + year + "-";

        Optional<WorkOrder> lastOrder = workOrderRepository
        .findTopByCompany_IdAndOrderCodeStartingWithOrderByOrderCodeDesc(
            companyId,
            prefix
        );

        int nextNumber = lastOrder
        .map(order -> Integer.parseInt(
            order.getOrderCode().substring(prefix.length())
        ) + 1)
        .orElse(1);

        return String.format("%s%06d", prefix, nextNumber);

    }

    public WorkOrderResponseDTO toResponseDTO(WorkOrder workOrder) {
        return new WorkOrderResponseDTO(
            workOrder.getId(), 
            workOrder.getCompany().getId(), 
            workOrder.getCompany().getName(), 
            workOrder.getOrderCode(), 
            workOrder.getCustomer().getId(), 
            workOrder.getCustomer().getName(), 
            workOrder.getCustomer().getLastName(), 
            workOrder.getService().getId(), 
            workOrder.getService().getName(), 
            workOrder.getProgrammedDate(), 
            workOrder.getServiceAddress(), 
            workOrder.getProblemDescription(), 
            workOrder.getPriority().getId(), 
            workOrder.getPriority().getName(), 
            workOrder.getWorkOrderStatus().getId(), 
            workOrder.getWorkOrderStatus().getName(), 
            workOrder.getEstimatedPrice(), 
            workOrder.getFinalPrice(), 
            workOrder.getFinishedAt()
        );
    }

}
