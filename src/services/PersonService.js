import { getItems, getItemById, addItem, deleteItem, updateItem } from "./Api";

export const getPersons = () => getItems("/persons");
export const addPerson = (part) => addItem("/persons", part);
export const deletePerson = (id) => deleteItem("/persons", id);
export const updatePerson = (part) => updateItem("/persons", part);
export const getPersonById = (id) => getItemById("/persons", id);
