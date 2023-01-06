package com.example.shoptienhieu.service.shippingAddressService;
import com.example.shoptienhieu.dto.request.shippingAddressReq.ShippingAddressRequest;
import com.example.shoptienhieu.entities.ShippingAddress;

import java.util.List;

public interface ShippingAddressService {public List<ShippingAddress> getAll();

    public ShippingAddress getById(int id);

    public void deleteById(int id);

    public List<ShippingAddress> getByCityId(int cityId);

    public List<ShippingAddress> getByUserId(int userId);

    public ShippingAddress create(ShippingAddressRequest shippingAddressRequest);

    public ShippingAddress updateById(int id, ShippingAddressRequest shippingAddressRequest);

    public void checkShippingAddress(ShippingAddressRequest shippingAddressRequest);

}
