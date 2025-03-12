import api from "./Api";

const getCategories = async () => {
  const response = await api.get("/categories");
  return response.data;
};

const addCategory = async (category) => {
  const response = await api.post("/categories", category);
  return response.data;
};

const deleteCategory = async (id) => {
  const response = await api.delete(`/categories/${id}`);
  return response.data;
};

const updateCategory = async (category) =>{
  const responce = await api.put(`/categories/${category.id}`, category);
  return responce.data;
}
const getCategoryById = async(id) =>{
  const responce = await api.get(`/categories/${id}`);
  return responce.data;
}

export { getCategories, addCategory, deleteCategory, updateCategory, getCategoryById };
