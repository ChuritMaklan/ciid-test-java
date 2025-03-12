import React, { useState, useEffect } from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import { updatePersonType } from "../../../services/PersonTypeService";

const PersonTypeUpdatePage = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const { id } = useParams();

  const [typeName, setTypeName] = useState("");

  useEffect(() => {
    if (location.state?.personType) {
      setTypeName(location.state.personType.typeName);
    }
  }, [location.state]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await updatePersonType({ id, typeName });
      navigate("/person-types"); // Перенаправление после обновления
    } catch (error) {
      console.error("Error updating person type:", error);
    }
  };

  return (
    <div className="container">
      <h1 className="title">Update Person Type</h1>
      <form className="form" onSubmit={handleSubmit}>
        <label>
          Type Name:
          <input
            type="text"
            value={typeName}
            onChange={(e) => setTypeName(e.target.value)}
            placeholder="Enter type name"
            className="input"
            required
          />
        </label>
        <button type="submit" className="button">
          Update Type
        </button>
      </form>
    </div>
  );
};

export default PersonTypeUpdatePage;
