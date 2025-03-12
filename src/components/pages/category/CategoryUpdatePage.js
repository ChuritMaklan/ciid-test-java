import React, { useState, useEffect } from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import { updateCategory } from "../../../services/CategoryService";

const CategoryUpdatePage = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const { id } = useParams();

  const [name, setName] = useState("");

  useEffect(() => {
    if (location.state?.category) {
      setName(location.state.category.name);
    }
  }, [location.state]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await updateCategory({ id, name });
      navigate("/categories"); // Перенаправление после обновления
    } catch (error) {
      console.error("Error updating category:", error);
    }
  };

  return (
    <div className="container">
      <h1 className="title">Update Category</h1>
      <form className="form" onSubmit={handleSubmit}>
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
        <button type="submit" className="button">
          Update Category
        </button>
      </form>
    </div>
  );
};

export default CategoryUpdatePage;
