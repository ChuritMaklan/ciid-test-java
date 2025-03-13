import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getPersonTypes, addPersonType, deletePersonType } from "../../../services/PersonTypeService";

const PersonTypePage = () => {
  const navigate = useNavigate();
  const [types, setTypes] = useState([]);
  const [typeName, setTypeName] = useState("");

  const fetchTypes = async () => {
    try {
      const data = await getPersonTypes();
      setTypes(data);
    } catch (error) {
      console.error("Error fetching person types:", error);
    }
  };

  const handleUpdatePersonType = (personType) => {
    navigate(`/person-types/update/${personType.id}`, { state: { personType } });
  };

  const handleAddType = async () => {
    if (!typeName) {
      alert("Type name is required");
      return;
    }
    try {
      await addPersonType(typeName);
      await fetchTypes();
      setTypeName("");
    } catch (error) {
      console.error("Error adding person type:", error);
    }
  };

  const handleDeleteType = async (id) => {
    try {
      await deletePersonType(id);
      await fetchTypes();
    } catch (error) {
      console.error("Error deleting person type:", error);
    }
  };

  useEffect(() => {
    fetchTypes();
  }, []);

  return (
    <div className="container">
      <h1 className="title">Person Types</h1>
      <form className="form" onSubmit={(e) => e.preventDefault()}>
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
        <button onClick={handleAddType} className="button">
          Add Type
        </button>
      </form>
      <ul className="list">
        {types.map((type) => (
          <li key={type.id} className="listItem">
            <div>
              <strong>{type.typeName}</strong>
            </div>
            <div>
              <button
                onClick={() => handleDeleteType(type.id)}
                className="button"
                style={{ marginRight: "10px", backgroundColor: "#dc3545" }}
              >
                Delete
              </button>
              <button onClick={() => handleUpdatePersonType(type)} className="button">
                Update
              </button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default PersonTypePage;
