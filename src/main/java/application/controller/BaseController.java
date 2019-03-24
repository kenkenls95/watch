package application.controller;

import application.data.service.BaseService;
import application.model.ResponseModel;
import org.springframework.web.bind.annotation.*;

public abstract class BaseController<T extends BaseService,K> implements IController {

    private T service;
    private K obj;

    public K getObj() {
        return obj;
    }

    public void setObj(K obj) {
        this.obj = obj;
    }

    @Override
    public T getService() {
        return service;
    }

    public void setService(T service) {
        this.service = service;
    }

    @GetMapping
    public ResponseModel getAll(){
        return getService().getAll();
    }

    @PostMapping
    public ResponseModel insert(@RequestBody K obj){
        return getService().insert(obj);
    }

    @PutMapping
    public ResponseModel update(@RequestBody K obj){
        return getService().update(obj);
    }


}
