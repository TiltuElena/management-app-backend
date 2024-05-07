package com.example.demo.util.mapping;

import com.example.demo.api.customer.dto.CustomerByIdDTO;
import com.example.demo.api.customer.model.Customer;
import com.example.demo.api.distributor.dto.DistributorByIdDTO;
import com.example.demo.api.distributor.model.Distributor;
import com.example.demo.api.employee.dto.EmployeeByIdDTO;
import com.example.demo.api.employee.model.Employee;
import com.example.demo.api.ingredient.dto.IngredientByIdDTO;
import com.example.demo.api.ingredient.model.Ingredient;
import com.example.demo.api.order.dto.OrderByIdDTO;
import com.example.demo.api.order.dto.ProductOrderDTO;
import com.example.demo.api.order.model.Order;
import com.example.demo.api.order.model.OrderProduct;
import com.example.demo.api.product.dto.ProductByIdDTO;
import com.example.demo.api.product.model.Product;
import com.example.demo.util.dto.IdNameDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.NONE)
public class EntityToDTOMappingUtil {

    public static CustomerByIdDTO mapToDTO(Customer customer) {
        CustomerByIdDTO customerByIdDTO = new CustomerByIdDTO();
        customerByIdDTO.setId(customer.getId());
        customerByIdDTO.setFirstName(customer.getFirstName());
        customerByIdDTO.setLastName(customer.getLastName());
        customerByIdDTO.setEmail(customer.getEmail());
        customerByIdDTO.setPhone(customer.getPhone());
        return customerByIdDTO;
    }

    public static EmployeeByIdDTO mapToDTO(Employee employee) {
        EmployeeByIdDTO employeeByIdDTO = new EmployeeByIdDTO();
        employeeByIdDTO.setId(employee.getId());
        employeeByIdDTO.setFirstName(employee.getFirstName());
        employeeByIdDTO.setLastName(employee.getLastName());
        employeeByIdDTO.setEmail(employee.getEmail());
        employeeByIdDTO.setPhone(employee.getPhone());
        employeeByIdDTO.setAddress(employee.getAddress());
        employeeByIdDTO.setSalary(employee.getSalary());
        return employeeByIdDTO;
    }

    public static IngredientByIdDTO mapToDTO(Ingredient ingredient) {
        IngredientByIdDTO ingredientByIdDTO = new IngredientByIdDTO();
        ingredientByIdDTO.setId(ingredient.getId());
        ingredientByIdDTO.setName(ingredient.getName());
        return ingredientByIdDTO;
    }

    public static ProductByIdDTO mapToDTO(Product product) {
        ProductByIdDTO productByIdDTO = new ProductByIdDTO();
        List<IdNameDTO> ingredients = new ArrayList<>();

        for (Ingredient ingredient : product.getIngredients()) {
            ingredients.add(new IdNameDTO(ingredient.getId(), ingredient.getName()));
        }

        productByIdDTO.setId(product.getId());
        productByIdDTO.setName(product.getName());
        productByIdDTO.setDescription(product.getDescription());
        productByIdDTO.setPrice(product.getPrice());
        productByIdDTO.setIngredients(ingredients);

        return productByIdDTO;
    }

    public static OrderByIdDTO mapToDTO(Order order) {
        OrderByIdDTO orderByIdDTO = new OrderByIdDTO();
        List<ProductOrderDTO> orderProducts = new ArrayList<>();

        for (OrderProduct orderProduct : order.getOrderProducts()) {
            ProductOrderDTO productOrderDTO = new ProductOrderDTO(orderProduct.getProduct().getId(), orderProduct.getQuantity());
            orderProducts.add(productOrderDTO);
        }

        orderByIdDTO.setId(order.getId());
        orderByIdDTO.setCustomer(new IdNameDTO(order.getCustomer().getId(), order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName()));
        orderByIdDTO.setDate(order.getDate());
        orderByIdDTO.setProducts(orderProducts);
        orderByIdDTO.setTotalPrice(order.getTotalPrice());

        return orderByIdDTO;
    }

    public static DistributorByIdDTO mapToDTO(Distributor distributor) {
        DistributorByIdDTO distributorByIdDTO = new DistributorByIdDTO();

        distributorByIdDTO.setId(distributor.getId());
        distributorByIdDTO.setPhone(distributor.getPhone());
        distributorByIdDTO.setEmail(distributor.getEmail());
        distributorByIdDTO.setProduct(new IdNameDTO(distributor.getProduct().getId(), distributor.getProduct().getName()));

        return distributorByIdDTO;
    }
}
