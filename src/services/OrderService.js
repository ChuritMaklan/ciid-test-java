import api from "./Api";

const getOrders = async () => {
  const response = await api.get("/orders");
  return response.data;
};
const getOrderById = async(id) =>{
  const responce = await api.get(`/orders/${id}`);
  return responce.data;
}

const addOrder = async (order) => {
  const response = await api.post("/orders", order);
  return response.data;
};

const deleteOrder = async (id) => {
  const response = await api.delete(`/orders/${id}`);
  return response.data;
};

const updateOrder = async (order) =>{
  const responce = await api.put(`/orders/${order.id}`, order);
  return responce.data;
}

export { getOrders, addOrder, deleteOrder, updateOrder, getOrderById };
