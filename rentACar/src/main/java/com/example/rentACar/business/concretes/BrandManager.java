package com.example.rentACar.business.concretes;

import com.example.rentACar.business.abstracts.BrandService;
import com.example.rentACar.business.requests.CreateBrandRequest;
import com.example.rentACar.business.requests.UpdateBrandRequest;
import com.example.rentACar.business.responses.GetAllBrandsResponse;
import com.example.rentACar.business.responses.GetByIdResponse;
import com.example.rentACar.business.rules.BrandBusinessRules;
import com.example.rentACar.common.utilities.mappers.ModelMapperServiceImpl;
import com.example.rentACar.Data.dataAccess.abstracts.BrandRepository;
import com.example.rentACar.Data.dataAccess.entities.concretes.Brand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

// Springde IOC otomatik olarak dependecyler aracılığıyla yapılır.
// BrandController sınıfında oluşturulan BrandManager nesnesi buradaki nesnedir. (IOC)
@Service
@NoArgsConstructor
@Getter
@Setter
public class BrandManager implements BrandService {
    private BrandRepository brandRepository;     // Data access layerle iletişim kurar (injection). Business'ın interface dışında data accessten haberi yok. Bu loosly coupled yani gevşek bağlıdır.
    private ModelMapperServiceImpl modelMapperService;
    private BrandBusinessRules brandBusinessRules;

    @Autowired
    public BrandManager(BrandRepository brandRepository, ModelMapperServiceImpl modelMapperService, BrandBusinessRules brandBusinessRules) {
        this.brandRepository = brandRepository;
        this.modelMapperService = modelMapperService;
        this.brandBusinessRules = brandBusinessRules;
    }

    @Override
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands = brandRepository.findAll();

        List<GetAllBrandsResponse> brandsResponse = brands.stream()
                .map(brand -> this.modelMapperService
                .forResponse()
                .map(brand, GetAllBrandsResponse.class)).collect(Collectors.toList());
        return brandsResponse;
    }

    @Override
    public GetByIdResponse getById(int id) {
        Brand brand = this.brandRepository.findById(id).orElseThrow();
        GetByIdResponse response = this.modelMapperService.forResponse().map(brand, GetByIdResponse.class);

        return response;
    }
    // brandRequest ekleme işlemi

    @Override
    public void add(CreateBrandRequest createBrandRequest) {
        this.brandBusinessRules.checkIfBrandNameExists(createBrandRequest.getName());    // iş kuralına göre kontrol edip var olan bir markanın eklenmesini engeller.

        Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);    // Buradaki olay şu: eldeki CreateBrandRequest tipindeki nesneyi Brand tipine çevirerek map ediyoruz.
        // Burada name gibi yüzlerce alan olabilirdi ve onları elle tek tek set etmemiz gerekecekti. Ancak bu kod verilen parametrelerin aynı isimli tüm attributelarını map ediyor.
        this.brandRepository.save(brand);
    }

    @Override
    public void update(UpdateBrandRequest updateBrandRequest) {
        Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);    // Buradaki olay şu: eldeki UpdateBrandRequest tipindeki nesneyi Brand tipine çevirerek map ediyoruz.
        this.brandRepository.save(brand);
    }

    @Override
    public void delete(int id) {
        this.brandRepository.deleteById(id);
    }

}

