import api from "./Api";

const getPersons = async () => {
  const response = await api.get("/persons");
  return response.data;
};

const addPerson = async (person) => {
  const response = await api.post("/persons", person);
  return response.data;
};

const deletePerson = async (id) => {
  const response = await api.delete(`/persons/${id}`);
  return response.data;
};
const updatePerson = async (person) =>{
  const responce = await api.put(`/persons/${person.id}`, person);
  return responce.data;
}
const getPersonById = async(id) =>{
  const responce = await api.get(`/persons/${id}`);
  return responce.data;
}

export { getPersons, addPerson, deletePerson, updatePerson, getPersonById };
