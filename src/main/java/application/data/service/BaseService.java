package application.data.service;

import application.common.Common;
import application.model.Meta;
import application.model.MetaDataStatus;
import application.model.ResponseModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

import static application.common.Common.setMetaData;

public abstract class BaseService<T extends JpaRepository, K> implements IService {
    private T repo;
    private K obj;

    public K getObj() {
        return obj;
    }

    public void setObj(K obj) {
        this.obj = obj;
    }

    public T getRepo() {
        return repo;
    }

    public void setRepo(T repo) {
        this.repo = repo;
    }

    @Override
    public T getRepository() {
        return repo;
    }

    @Autowired
    ModelMapper modelMapper;

    public void setRepository(T repository) {
        repo = repository;
    }

    public ResponseModel getAll() {
        try {
            return setMetaData(getRepository().findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return setMetaData(MetaDataStatus.FAILED);
        }
    }

    public ResponseModel delete(int id) {
        ResponseModel responseModel = new ResponseModel();
        Meta meta = new Meta();
        try {
            getRepository().delete(id);
            return setMetaData(responseModel, meta, MetaDataStatus.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return setMetaData(responseModel, meta, MetaDataStatus.FAILED);
        }
    }

    public ResponseModel insert(K obj) {
        ResponseModel responseModel = new ResponseModel();
        Meta meta = new Meta();
        try {
            getRepository().save(obj);
            return setMetaData(responseModel, meta, MetaDataStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return setMetaData(responseModel, meta, MetaDataStatus.FAILED);
        }
    }

    public ResponseModel update(K obj) {
        ResponseModel responseModel = new ResponseModel();
        Meta meta = new Meta();
        try {
            getRepository().save(obj);
            return Common.setMetaData(responseModel, meta, MetaDataStatus.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return setMetaData(responseModel, meta, MetaDataStatus.FAILED);
        }
    }

    public ResponseModel deleteAll() {
        ResponseModel responseModel = new ResponseModel();
        Meta meta = new Meta();
        try {
            getRepository().deleteAll();
            return Common.setMetaData(responseModel, meta, MetaDataStatus.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return setMetaData(responseModel, meta, MetaDataStatus.FAILED);
        }
    }
}
