import api from "./Api";

const getParts = async () => {
  const response = await api.get("/parts");
  return response.data;
};

const addPart = async (part) => {
  const response = await api.post("/parts", part);
  return response.data;
};

const deletePart = async (id) => {
  const response = await api.delete(`/parts/${id}`);
  return response.data;
};
const updatePart = async (part) =>{
  const responce = await api.put(`/parts/${part.id}`, part);
  return responce.data;
}

const getPartById = async(id) =>{
  const responce = await api.get(`/parts/${id}`);
  return responce.data;
}

export { getParts, addPart, deletePart, updatePart, getPartById };
