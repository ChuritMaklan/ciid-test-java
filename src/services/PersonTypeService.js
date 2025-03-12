import api from "./Api";

const getPersonTypes = async () => {
  const response = await api.get("/person-types");
  return response.data;
};

const addPersonType = async (typeName) => {
  const response = await api.post("/person-types", { typeName });
  return response.data;
};

const deletePersonType = async (id) => {
  const response = await api.delete(`/person-types/${id}`);
  return response.data;
};
const updatePersonType = async (personType) =>{
    const responce = await api.put(`/person-types/${personType.id}`, personType);
    return responce.data;
};
const getPersonTypeById = async(id) =>{
    const responce = await api.get(`/person-types/${id}`);
    return responce.data;
}

export { getPersonTypes, addPersonType, deletePersonType, updatePersonType, getPersonTypeById };
