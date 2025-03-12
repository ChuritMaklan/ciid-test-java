import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getCategories, addCategory, deleteCategory } from "../../../services/CategoryService";

const CategoryPage = () => {
  const navigate = useNavigate();
  const [categories, setCategories] = useState([]);
  const [name, setName] = useState("");

  const fetchCategories = async () => {
    try {
      const data = await getCategories();
      setCategories(data);
    } catch (error) {
      console.error("Error fetching categories:", error);
    }
  };

  const handleUpdateCategory = (category) => {
    navigate(`/categories/update/${category.id}`, { state: { category } });
  };

  const handleAddCategory = async () => {
    if (!name) {
      alert("Category name is required");
      return;
    }
    try {
      await addCategory({ name });
      fetchCategories();
      setName("");
    } catch (error) {
      console.error("Error adding category:", error);
    }
  };

  const handleDeleteCategory = async (id) => {
    try {
      await deleteCategory(id);
      fetchCategories();
    } catch (error) {
      console.error("Error deleting category:", error);
    }
  };

  useEffect(() => {
    fetchCategories();
  }, []);

  return (
    <div className="container">
      <h1 className="title">Categories</h1>
      <form className="form" onSubmit={(e) => e.preventDefault()}>
        <label>
          Category Name:
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            placeholder="Enter category name"
            className="input"
            required
          />
        </label>
        <button onClick={handleAddCategory} className="button">
          Add Category
        </button>
      </form>
      <ul className="list">
        {categories.map((category) => (
          <li key={category.id} className="listItem">
            <div>
              <strong>{category.name}</strong>
            </div>
            <div>
              <button
                onClick={() => handleDeleteCategory(category.id)}
                className="button"
                style={{ marginRight: "10px", backgroundColor: "#dc3545" }}
              >
                Delete
              </button>
              <button onClick={() => handleUpdateCategory(category)} className="button">
                Update
              </button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default CategoryPage;
