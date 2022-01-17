package io.github.prozorowicz.lang;

import java.util.List;
import static java.util.stream.Collectors.toList;

class LangService {
    private LangRepository repository;

    LangService() {
        this(new LangRepository());
    }

    LangService(LangRepository repository) {
        this.repository = repository;
    }

    List<LangDTO> findAll() {
/*
        var langList = repository.findAll();
        var result = new ArrayList<LangDTO>();
        for(Lang lang:langList){
            result.add(new LangDTO(lang));
        }
        return result;
*/
        return repository
                .findAll()
                .stream()
                .map(LangDTO::new)
                .collect(toList());
    }
}
