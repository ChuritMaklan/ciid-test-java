import { getItems, getItemById, addItem, deleteItem, updateItem } from "./Api";

export const getPersonTypes = () => getItems("/person-types");
export const addPersonType = (part) => addItem("/person-types", part);
export const deletePersonType = (id) => deleteItem("/person-types", id);
export const updatePersonType = (part) => updateItem("/person-types", part);
export const getPersonTypeById = (id) => getItemById("/person-types", id);
