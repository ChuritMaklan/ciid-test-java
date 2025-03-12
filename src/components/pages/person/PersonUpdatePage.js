import React, { useState, useEffect } from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import { updatePerson } from "../../../services/PersonService";
import { getPersonTypes } from "../../../services/PersonTypeService";

const PersonUpdatePage = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const { id } = useParams();

  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [phone, setPhone] = useState("");
  const [typeId, setTypeId] = useState("");
  const [personTypes, setPersonTypes] = useState([]);

  useEffect(() => {
    if (location.state?.person) {
      const { name, email, phone, typeId } = location.state.person;
      setName(name);
      setEmail(email);
      setPhone(phone);
      setTypeId(typeId);
      fetchPersonTypes();
    }
  }, [location.state]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await updatePerson({ id, name, email, phone, typeId });
      navigate("/persons"); // Перенаправление после обновления
    } catch (error) {
      console.error("Error updating person:", error);
    }
  };

  const fetchPersonTypes = async () => {
    try {
      const data = await getPersonTypes();
      setPersonTypes(data);
    } catch (error) {
      console.error("Error fetching person types:", error);
    }
  };

  return (
    <div className="container">
      <h1 className="title">Update Person</h1>
      <form className="form" onSubmit={handleSubmit}>
        <label>
          Name:
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            placeholder="Enter name"
            className="input"
            required
          />
        </label>

        <label>
          Email:
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            placeholder="Enter email"
            className="input"
            required
          />
        </label>

        <label>
          Phone:
          <input
            type="text"
            value={phone}
            onChange={(e) => setPhone(e.target.value)}
            placeholder="Enter phone"
            className="input"
            required
          />
        </label>

        <label>
          Select Person Type:
          <select
            value={typeId}
            onChange={(e) => setTypeId(e.target.value)}
            className="select"
            required
          >
            <option value="" disabled>
              Select Person Type
            </option>
            {personTypes.map((type) => (
              <option key={type.id} value={type.id}>
                {type.typeName}
              </option>
            ))}
          </select>
        </label>

        <button type="submit" className="button">
          Update Person
        </button>
      </form>
    </div>
  );
};

export default PersonUpdatePage;
