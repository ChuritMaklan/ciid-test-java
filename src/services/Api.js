import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    "Content-Type": "application/json",
  },
});

const methodCall = async (method, subURL, data = null) => {
  const response = await api({
    method,
    url: subURL,
    data,
  });
  return response. data;
};

export const getItems = (subURL) => methodCall("get", subURL);
export const addItem = (subURL, item) => methodCall("post", subURL, item);
export const deleteItem = (subURL, id) => methodCall("delete", `${subURL}/${id}`);
export const updateItem = (subURL, item) => methodCall("put", `${subURL}/${item.id}`, item);
export const getItemById = (subURL, id) => methodCall("get", `${subURL}/${id}`);
