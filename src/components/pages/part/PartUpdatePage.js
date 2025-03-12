import React, { useState, useEffect } from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import { updatePart, getPartById } from "../../../services/PartService";
import { getPersons } from "../../../services/PersonService";
import { getCategories } from "../../../services/CategoryService";

const UpdatePartPage = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const { id } = useParams();

  const [persons, setPersons] = useState([]);
  const [categories, setCategories] = useState([]);
  const [name, setName] = useState("");
  const [price, setPrice] = useState("");
  const [description, setDescription] = useState("");
  const [personId, setPersonId] = useState("");
  const [categoryIds, setCategoryIds] = useState([]);
  const [quantityInStock, setQuantityInStock] = useState("");

  useEffect(() => {
    fetchPersons();
    fetchCategories();

    if (location.state?.part) {
      const { name, price, description, personId, categoryIds, quantityInStock } = location.state.part;
      setName(name);
      setPrice(price);
      setDescription(description);
      setPersonId(personId);
      setCategoryIds(categoryIds);
      setQuantityInStock(quantityInStock);
    }
  }, [location.state]);

  const fetchPersons = async () => {
    try {
      const data = await getPersons();
      setPersons(data);
    } catch (error) {
      console.error("Error fetching persons:", error);
    }
  };

  const fetchCategories = async () => {
    try {
      const data = await getCategories();
      setCategories(data);
    } catch (error) {
      console.error("Error fetching categories:", error);
    }
  };

  const handleCategoryChange = (e) => {
    const selectedOptions = Array.from(e.target.selectedOptions);
    const selectedIds = selectedOptions.map((option) => option.value);
    setCategoryIds(selectedIds);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const partData = {
        id: Number(id),
        name,
        price: Number(price),
        description,
        personId: Number(personId),
        categoryIds: categoryIds.map((id) => Number(id)),
        quantityInStock: Number(quantityInStock),
      };
      await updatePart(partData);
      navigate("/parts");
    } catch (error) {
      console.error("Error updating part:", error);
    }
  };

  return (
    <div className="container">
      <h1 className="title">Update Part</h1>
      <form className="form" onSubmit={handleSubmit}>
        {/* Part Name */}
        <label>
          Part Name:
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            placeholder="Enter part name"
            className="input"
            required
          />
        </label>

        {/* Price */}
        <label>
          Price:
          <input
            type="number"
            value={price}
            onChange={(e) => setPrice(e.target.value)}
            placeholder="Enter price"
            className="input"
            required
          />
        </label>

        {/* Description */}
        <label>
          Description:
          <input
            type="text"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            placeholder="Enter description"
            className="input"
            required
          />
        </label>

        {/* Quantity in Stock */}
        <label>
          Quantity in Stock:
          <input
            type="number"
            value={quantityInStock}
            onChange={(e) => setQuantityInStock(e.target.value)}
            placeholder="Enter quantity"
            className="input"
            required
          />
        </label>

        {/* Person */}
        <label>
          Select Person:
          <select
            value={personId}
            onChange={(e) => setPersonId(e.target.value)}
            className="select"
            required
          >
            <option value="" disabled>
              Select a person
            </option>
            {persons.map((person) => (
              <option key={person.id} value={person.id}>
                {person.name}
              </option>
            ))}
          </select>
        </label>

        {/* Categories */}
        <label>
          Select Categories (Hold Ctrl to select multiple):
          <select
            multiple
            value={categoryIds}
            onChange={handleCategoryChange}
            className="select"
            required
          >
            <option value="" disabled>
              Select categories
            </option>
            {categories.map((category) => (
              <option key={category.id} value={category.id}>
                {category.name}
              </option>
            ))}
          </select>
        </label>

        <button type="submit" className="button">
          Update Part
        </button>
      </form>
    </div>
  );
};

export default UpdatePartPage;
