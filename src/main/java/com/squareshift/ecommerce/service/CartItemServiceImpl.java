package com.squareshift.ecommerce.service;

import com.squareshift.ecommerce.constants.Constants;
import com.squareshift.ecommerce.dto.CartItemsAddResponseDto;
import com.squareshift.ecommerce.dto.ProductDto;
import com.squareshift.ecommerce.dto.WarehouseResponseDto;
import com.squareshift.ecommerce.entity.CartItems;
import com.squareshift.ecommerce.exception.CustomException;
import com.squareshift.ecommerce.exception.EntityNotFoundException;
import com.squareshift.ecommerce.repository.CartItemsRepository;
import com.squareshift.ecommerce.request.AddItemRequest;
import com.squareshift.ecommerce.request.DeleteItemRequest;
import com.squareshift.ecommerce.response.Items;
import com.squareshift.ecommerce.response.MetaResponse;
import com.squareshift.ecommerce.response.ResponseData;
import com.squareshift.ecommerce.response.ResponseItemsData;
import com.squareshift.ecommerce.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = CustomException.class)
public class CartItemServiceImpl implements CartItemsService{

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private WareHouseService wareHouseService;


    @Override
    public ResponseEntity<Object> addCartItem(AddItemRequest addItemRequest) {
        try {
            ProductDto productDto = productService.getProductById(addItemRequest.getProductID());
            CartItems cartItems=new CartItems();
            cartItems.setProductID(addItemRequest.getProductID());
            cartItems.setQuantity(addItemRequest.getQuantity());
          try {
              boolean exists = cartItemsRepository.existsByProductID(addItemRequest.getProductID());
              CartItems savedItem=null;
              int updatedQuery=0;
              if(exists){
               //   int c =cartItemsRepository.deleteByProductID(addItemRequest.getProductID());
                    updatedQuery = cartItemsRepository.updateQuantity(addItemRequest.getQuantity(),addItemRequest.getProductID());
              }else{
                  savedItem = cartItemsRepository.save(cartItems);
              }
              if( updatedQuery!=0||savedItem.getProductID()==cartItems.getProductID()){
                  return new ResponseEntity<>(new ResponseData<>(Constants.SUCCESS,Constants.ITEM_ADDED), HttpStatus.OK);
              }
          }catch (Exception e){
              throw new CustomException(e.getMessage());
          }
        }catch (Exception e){
            throw new EntityNotFoundException(Constants.INVALID_PRODUCT_ID);
        }
        return null;
    }

    @Override
    public ResponseEntity<Object> getCartItem() {
        List<CartItems> allItemsList = cartItemsRepository.findAll();
        List<Items>itemsList=new ArrayList<>();
        if(allItemsList!=null && !allItemsList.isEmpty()){
            for(CartItems item:allItemsList){
                try {
                    ProductDto productDto = productService.getProductById(item.getProductID());
                    Items items=new Items(item.getProductID(),productDto.getDescription(),item.getQuantity());
                    itemsList.add(items);
                }catch (Exception e){
                    throw new CustomException(Constants.INVALID_PRODUCT_ID);
                }
            }
        }else{
            throw new EntityNotFoundException(Constants.CART_EMPTY);
        }
        return new ResponseEntity<>(new ResponseItemsData<>(Constants.SUCCESS,Constants.CART_DATA,itemsList),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteCartItem(DeleteItemRequest deleteItemRequest) {
        try{
            cartItemsRepository.deleteAll();
            return new ResponseEntity<>(new ResponseData<>(Constants.SUCCESS,Constants.ITEM_DELETED), HttpStatus.OK);
        }catch(Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> getTotalValue(long postalCode) {
        try {
            WarehouseResponseDto warehouseResponseDto = wareHouseService.getWareHouseDistanceByPostalCode(postalCode);
            List<CartItems> allItemsList = cartItemsRepository.findAll();
            if(allItemsList==null||allItemsList.isEmpty()){
                throw new CustomException(Constants.BAD_REQUEST);
            }
            long weight=0;
            for(CartItems cartItems:allItemsList){
                ProductDto productDto = productService.getProductById(cartItems.getProductID());
                weight+=productDto.getWeight_in_grams()*cartItems.getQuantity();
            }
            float amount= CommonUtils.calculateTotalAmount(warehouseResponseDto.getDistance_in_kilometers(),weight);
            return new ResponseEntity<>(new ResponseData<>(Constants.SUCCESS,Constants.TOTAL_AMOUNT+Float.valueOf(amount)), HttpStatus.OK);
        }catch (Exception e){
            throw new EntityNotFoundException(Constants.INVALID_POSTAL);

        }
    }
}
