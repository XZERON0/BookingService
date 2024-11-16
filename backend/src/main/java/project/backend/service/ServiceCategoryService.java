package project.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.backend.repository.ServiceCategoryRepository;
@Service
public class ServiceCategoryService {
    @Autowired
    private ServiceCategoryRepository serviceCategoryRepository;

    

}