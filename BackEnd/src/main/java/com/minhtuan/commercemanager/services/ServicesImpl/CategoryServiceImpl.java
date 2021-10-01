package com.minhtuan.commercemanager.services.ServicesImpl;

import com.minhtuan.commercemanager.dto.CategoryDTO;
import com.minhtuan.commercemanager.dto.ProviderDTO;
import com.minhtuan.commercemanager.maper.CategoryMapper;
import com.minhtuan.commercemanager.maper.ProviderMaper;
import com.minhtuan.commercemanager.message.request.CategoryRequest;
import com.minhtuan.commercemanager.model.Category;
import com.minhtuan.commercemanager.model.Provider;
import com.minhtuan.commercemanager.model.Room;
import com.minhtuan.commercemanager.repository.CategoryRepository;
import com.minhtuan.commercemanager.repository.ProviderRepository;
import com.minhtuan.commercemanager.repository.RoomRepository;
import com.minhtuan.commercemanager.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public CategoryDTO save(CategoryRequest categoryRequest) {
        Category category = new Category();
        List<Category> categoryList = categoryRepository.findAll();
        String idNew = "";
        if(categoryList.size() > 0){
            Integer end = categoryList.size() - 1;
            String id = categoryList.get(end).getCategoryId();
            String IdInt = id.substring(3);
            String IdBegin = id.substring(0,3);
            Integer newIdInt = Integer.parseInt(IdInt);
            newIdInt += 1;
            String newIdString = newIdInt.toString();
            if(newIdString.length() == 1)
            {
                newIdString = "0000" + newIdString;
            }else if(newIdString.length() == 2)
            {
                newIdString = "000" + newIdString;
            }else if(newIdString.length() == 3)
            {
                newIdString = "00" + newIdString;
            }
            else if(newIdString.length() == 4)
            {
                newIdString = "0" + newIdString;
            }
            idNew = IdBegin + newIdString;
        }else {
            idNew = "DMH00001";
        }
        category.setCategoryId(idNew);
        category.setCategoryName(categoryRequest.getCategoryName());
        Room room = roomRepository.findRoomByRoomId(categoryRequest.getRoom());
        category.setRoom(room);
        category.setStatus(1);
        categoryRepository.save(category);
        CategoryDTO categoryDTO = categoryMapper.toDTO(category);
        return categoryDTO;
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> categoryList = categoryRepository.findAllByStatusOrderByCategoryId(1);
        List<CategoryDTO> categoryDTOList = categoryList.stream().map(category -> categoryMapper.toDTO(category)).collect(Collectors.toList());
        return categoryDTOList;
    }

    @Override
    public List<CategoryDTO> getAllCategoryByRoom(Integer roomId) {
        Room room = roomRepository.getById(roomId);
        List<Category> categoryList = categoryRepository.findAllByRoomAndStatusOrderByCategoryId(room, 1);
        List<CategoryDTO> categoryDTOList = categoryList.stream().map(category -> categoryMapper.toDTO(category)).collect(Collectors.toList());
        return categoryDTOList;
    }

    @Override
    public CategoryDTO getCategoryByCategoryId(String categoryId) {
        Category category = categoryRepository.getById(categoryId);
        CategoryDTO categoryDTO = categoryMapper.toDTO(category);
        return categoryDTO;
    }

    @Override
    public CategoryDTO updateCategoryByCategoryId(String categoryId, CategoryRequest categoryRequest) {
        Category category = categoryRepository.getById(categoryId);
        category.setCategoryName(categoryRequest.getCategoryName());
        Room room = roomRepository.getById(categoryRequest.getRoom());
        category.setRoom(room);
        categoryRepository.save(category);
        CategoryDTO categoryDTO = categoryMapper.toDTO(category);
        return categoryDTO;
    }

    @Override
    public void deleteCategory(String categoryId) {
        Category category = categoryRepository.getById(categoryId);
        category.setStatus(0);
        categoryRepository.save(category);
    }
}
