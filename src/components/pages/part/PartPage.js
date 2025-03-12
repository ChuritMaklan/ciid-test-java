import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getParts, addPart, deletePart } from "../../../services/PartService";
import { getPersons } from "../../../services/PersonService";
import { getCategories } from "../../../services/CategoryService";

const PartsPage = () => {
  const navigate = useNavigate();
  const [parts, setParts] = useState([]);
  const [persons, setPersons] = useState([]);
  const [categories, setCategories] = useState([]);
  const [name, setName] = useState("");
  const [price, setPrice] = useState("");
  const [quantity, setQuantity] = useState("");
  const [description, setDescription] = useState("");
  const [personId, setPersonId] = useState("");
  const [categoryIds, setCategoryIds] = useState([]);

  const fetchParts = async () => {
    try {
      const data = await getParts();
      setParts(data);
    } catch (error) {
      console.error("Error fetching parts:", error);
    }
  };

  const handleUpdatePart = (part) => {
    navigate(`/parts/update/${part.id}`, { state: { part } });
  };

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

  const handleAddPart = async () => {
    if (!name || !price || !quantity || !description || !personId || categoryIds.length === 0) {
      alert("All fields are required");
      return;
    }
    try {
      await addPart({
        name,
        price,
        quantityInStock: quantity,
        description,
        personId,
        categoryIds,
      });
      fetchParts();
      setName("");
      setPrice("");
      setQuantity("");
      setDescription("");
      setPersonId("");
      setCategoryIds([]);
    } catch (error) {
      console.error("Error adding part:", error);
    }
  };

  const handleDeletePart = async (id) => {
    try {
      await deletePart(id);
      fetchParts();
    } catch (error) {
      console.error("Error deleting part:", error);
    }
  };

  const handleCategoryChange = (e) => {
    const selectedOptions = Array.from(e.target.selectedOptions);
    const selectedIds = selectedOptions.map((option) => option.value);
    setCategoryIds(selectedIds);
  };

  useEffect(() => {
    fetchParts();
    fetchPersons();
    fetchCategories();
  }, []);

  return (
    <div className="container">
      <h1 className="title">Parts</h1>
      <form className="form" onSubmit={(e) => e.preventDefault()}>
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

        {/* Quantity in Stock */}
        <label>
          Quantity in Stock:
          <input
            type="number"
            value={quantity}
            onChange={(e) => setQuantity(e.target.value)}
            placeholder="Enter quantity"
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

        <button onClick={handleAddPart} className="button">
          Add Part
        </button>
      </form>
      <ul className="list">
        {parts.map((part) => (
          <li key={part.id} className="listItem">
            <div>
              <strong>{part.name}</strong> (${part.price}) - {part.quantityInStock} in stock
            </div>
            <div>
              <button
                onClick={() => handleDeletePart(part.id)}
                className="button"
                style={{ marginRight: "10px", backgroundColor: "#dc3545" }}
              >
                Delete
              </button>
              <button onClick={() => handleUpdatePart(part)} className="button">
                Update
              </button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default PartsPage;
