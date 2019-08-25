/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

"use strict";

let module = angular.module('CustomersModule', ['ngResource']); //Create CustomersModule

    module.factory('CustomerApi', function ($resource) { //Returns Resource Of Customer Service 
        return $resource('http://localhost:8080/api/Customers')
})

    module.factory('CreateCustomerApiJetty', function ($resource) { //Returns Resource Of Jetty Server And Provides HTTP Method To Be Called
          return $resource('http://localhost:9000',
              null, {update: {method: 'POST'}});
})

        module.factory('CreateCustomerApiAjax', function ($resource) { //Returns Resource Of Customer Service And Provides HTTP Method To Be Called
          return $resource('http://localhost:8080/api/Customers',
              null, {update: {method: 'POST'}});
})


module.controller('CustomersController', function(CustomerApi, CreateCustomerApiJetty, CreateCustomerApiAjax) { //Create Customer Controller With Provided Modules
let ctrl = this
ctrl.customers = CustomerApi.query();

    this.addCustomer = function (customerToAdd) { //Add Customer Method Called When Button In HTML Is Pressed
        CreateCustomerApiJetty.save({}, customerToAdd, function() {
            ctrl.customers = CustomerApi.query();
        })
        CreateCustomerApiAjax.save({}, customerToAdd, function() {
            ctrl.customers = CustomerApi.query();
        })
    }
})

