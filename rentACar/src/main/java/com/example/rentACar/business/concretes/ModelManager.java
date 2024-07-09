package com.example.rentACar.business.concretes;

import com.example.rentACar.business.abstracts.ModelService;
import com.example.rentACar.business.requests.CreateModelRequest;
import com.example.rentACar.business.responses.GetAllModelsResponse;
import com.example.rentACar.business.responses.GetByIdResponse;
import com.example.rentACar.business.rules.ModelBusinessRules;
import com.example.rentACar.common.utilities.mappers.ModelMapperService;
import com.example.rentACar.Data.dataAccess.abstracts.ModelRepository;
import com.example.rentACar.Data.dataAccess.entities.concretes.Model;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@Getter
@Setter
public class ModelManager implements ModelService {

    private ModelRepository modelRepository;
    private ModelMapperService modelMapperService;
    private ModelBusinessRules modelBusinessRules;

    @Autowired
    public ModelManager(ModelRepository modelRepository, ModelMapperService modelMapperService, ModelBusinessRules modelBusinessRules) {
        this.modelRepository = modelRepository;
        this.modelMapperService = modelMapperService;
        this.modelBusinessRules = modelBusinessRules;
    }

    @Override
    public List<GetAllModelsResponse> getAll() {
        List<Model> models = modelRepository.findAll();

        List<GetAllModelsResponse> modelsResponse = models.stream()
                .map(model -> this.modelMapperService
                        .forResponse()
                        .map(model, GetAllModelsResponse.class)).collect(Collectors.toList());

        return modelsResponse;
    }

    @Override
    public void add(@RequestBody() @Valid CreateModelRequest createModelRequest) {      // CreateRequest sınıflarında yaptığımız kısıtların çalışması için buradaki Valid annotasyonunu kullanmamız gerekiyo.
        this.modelBusinessRules.checkIfModelExists(createModelRequest.getName());       // Eğer model varsa hata fırlatacak.

        Model model = this.modelMapperService.forRequest().map(createModelRequest, Model.class);    // Buradaki olay şu: eldeki CreateBrandRequest tipindeki nesneyi Brand tipine çevirerek map ediyoruz.
        // Burada name gibi yüzlerce alan olabilirdi ve onları elle tek tek set etmemiz gerekecekti. Ancak bu kod verilen parametrelin aynı isimli tüm attributelarını map ediyor.
        this.modelRepository.save(model);
    }

    @Override
    public GetByIdResponse getById(int id) {
        Model model = this.modelRepository.findById(id).orElseThrow();
        GetByIdResponse response = this.modelMapperService.forResponse().map(model, GetByIdResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        modelRepository.deleteById(id);
    }
}
