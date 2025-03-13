import { getItems, getItemById, addItem, deleteItem, updateItem } from "./Api";

export const getCategories = () => getItems("/categories");
export const addCategory = (part) => addItem("/categories", part);
export const deleteCategory = (id) => deleteItem("/categories", id);
export const updateCategory = (part) => updateItem("/categories", part);
export const getCategoryById = (id) => getItemById("/categories", id);
