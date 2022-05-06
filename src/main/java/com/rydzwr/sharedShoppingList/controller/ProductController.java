package com.rydzwr.sharedShoppingList.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController
{
    /*
    private final ProductRepository repository;

    public ProductController(ProductRepository repository)
    {
        this.repository = repository;
    }

    @GetMapping("/products")
    ResponseEntity<List<Product>> readAllProducts()
    {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/products/{id}")
    ResponseEntity<Product> readTask(@PathVariable int id)
    {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/products")
    ResponseEntity<Product> addNewProduct(@RequestBody Product product)
    {
        repository.save(product);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/products/{id}")
    ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody @Valid Product toUpdate)
    {
        if (!repository.existsById(id))
            return ResponseEntity.notFound().build();

        repository.findById(id)
                .ifPresent(product -> {
                    product.updateForm(toUpdate);
                    repository.save(product);
                });

        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PatchMapping("/products/{id}")
    public ResponseEntity<?> toggleProduct(@PathVariable int id)
    {
        if (!repository.existsById(id))
            return ResponseEntity.notFound().build();

        repository.findById(id).ifPresent(task -> task.setBought(!task.isBought()));
        return ResponseEntity.noContent().build();
    }

     */

}
