import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getPersons, addPerson, deletePerson } from "../../../services/PersonService";
import { getPersonTypes } from "../../../services/PersonTypeService";

const PersonPage = () => {
  const navigate = useNavigate();
  const [persons, setPersons] = useState([]);
  const [personTypes, setPersonTypes] = useState([]);
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [phone, setPhone] = useState("");
  const [typeId, setTypeId] = useState("");

  const fetchPersons = async () => {
    try {
      const data = await getPersons();
      setPersons(data);
    } catch (error) {
      console.error("Error fetching persons:", error);
    }
  };

  const handleUpdatePerson = (person) => {
    navigate(`/persons/update/${person.id}`, { state: { person } });
  };

  const fetchPersonTypes = async () => {
    try {
      const data = await getPersonTypes();
      setPersonTypes(data);
    } catch (error) {
      console.error("Error fetching person types:", error);
    }
  };

  const handleAddPerson = async () => {
    if (!name || !email || !phone || !typeId) {
      alert("All fields are required");
      return;
    }
    try {
      await addPerson({ name, email, phone, typeId });
      fetchPersons();
      setName("");
      setEmail("");
      setPhone("");
      setTypeId("");
    } catch (error) {
      console.error("Error adding person:", error);
    }
  };

  const handleDeletePerson = async (id) => {
    try {
      await deletePerson(id);
      fetchPersons();
    } catch (error) {
      console.error("Error deleting person:", error);
    }
  };

  useEffect(() => {
    fetchPersons();
    fetchPersonTypes();
  }, []);

  return (
    <div className="container">
      <h1 className="title">Persons</h1>
      <form className="form" onSubmit={(e) => e.preventDefault()}>
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

        <button onClick={handleAddPerson} className="button">
          Add Person
        </button>
      </form>
      <ul className="list">
        {persons.map((person) => {
          const personType = personTypes.find((type) => type.id === person.typeId);
          return (
            <li key={person.id} className="listItem">
              <div>
                <strong>{person.name}</strong> ({person.email}) <br />
                Phone: {person.phone} <br />
                Type: {personType ? personType.typeName : "N/A"}
              </div>
              <div>
                <button
                  onClick={() => handleDeletePerson(person.id)}
                  className="button"
                  style={{ marginRight: "10px", backgroundColor: "#dc3545" }}
                >
                  Delete
                </button>
                <button onClick={() => handleUpdatePerson(person)} className="button">
                  Update
                </button>
              </div>
            </li>
          );
        })}
      </ul>
    </div>
  );
};

export default PersonPage;
