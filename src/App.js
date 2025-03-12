import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Navbar from "./components/Navbar";
import Person from "./components/pages/person/PersonPage";
import Part from "./components/pages/part/PartPage";
import Category from "./components/pages/category/CategoryPage";
import Order from "./components/pages/order/OrderPage";
import PersonType from "./components/pages/personType/PersonTypePage";
import PersonUpdate from "./components/pages/person/PersonUpdatePage";
import PartUpdate from "./components/pages/part/PartUpdatePage";
import CategoryUpdate from "./components/pages/category/CategoryUpdatePage";
import OrderUpdate from "./components/pages/order/OrderUpdatePage";
import PersonTypeUpdate from "./components/pages/personType/PersonTypeUpdatePage";
import "./global.css"; 

function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/persons" element={<Person />} />
        <Route path="/person-types" element={<PersonType />} />
        <Route path="/parts" element={<Part />} />
        <Route path="/categories" element={<Category />} />
        <Route path="/orders" element={<Order />} />
        <Route path="/persons/update/:id" element={<PersonUpdate />} />
        <Route path="/person-types/update/:id" element={<PersonTypeUpdate />} />
        <Route path="/parts/update/:id" element={<PartUpdate />} />
        <Route path="/categories/update/:id" element={<CategoryUpdate />} />
        <Route path="/orders/update/:id" element={<OrderUpdate />} />  
      </Routes>
    </Router>
  );
}

export default App;
