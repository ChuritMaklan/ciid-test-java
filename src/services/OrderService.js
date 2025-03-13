import { getItems, getItemById, addItem, deleteItem, updateItem } from "./Api";

export const getOrders = () => getItems("/orders");
export const addOrder = (part) => addItem("/orders", part);
export const deleteOrder = (id) => deleteItem("/orders", id);
export const updateOrder = (part) => updateItem("/orders", part);
export const getOrderById = (id) => getItemById("/orders", id);
