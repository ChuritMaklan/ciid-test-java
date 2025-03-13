import { getItems, getItemById, addItem, deleteItem, updateItem } from "./Api";

export const getParts = () => getItems("/parts");
export const addPart = (part) => addItem("/parts", part);
export const deletePart = (id) => deleteItem("/parts", id);
export const updatePart = (part) => updateItem("/parts", part);
export const getPartById = (id) => getItemById("/parts", id);
