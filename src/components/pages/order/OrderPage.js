import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getOrders, addOrder, deleteOrder } from "../../../services/OrderService";
import { getPersons } from "../../../services/PersonService";
import { getParts } from "../../../services/PartService";

const OrderPage = () => {
  const navigate = useNavigate();
  const [orders, setOrders] = useState([]);
  const [persons, setPersons] = useState([]);
  const [parts, setParts] = useState([]);
  const [personId, setPersonId] = useState("");
  const [orderDate, setOrderDate] = useState("");
  const [selectedParts, setSelectedParts] = useState([]);

  const fetchOrders = async () => {
    try {
      const data = await getOrders();
      setOrders(data);
    } catch (error) {
      console.error("Error fetching orders:", error);
    }
  };

  const handleUpdateOrder = (order) => {
    navigate(`/orders/update/${order.id}`, { state: { order } });
  };

  const fetchPersons = async () => {
    try {
      const data = await getPersons();
      setPersons(data);
    } catch (error) {
      console.error("Error fetching persons:", error);
    }
  };

  const fetchParts = async () => {
    try {
      const data = await getParts();
      setParts(data);
    } catch (error) {
      console.error("Error fetching parts:", error);
    }
  };

  const handleAddOrder = async () => {
    if (!personId || !orderDate || selectedParts.length === 0) {
      alert("Person, Order Date, and at least one part are required");
      return;
    }
    try {
      const orderRequest = {
        personId: Number(personId),
        orderDate: new Date(orderDate).toISOString(),
        orderItems: selectedParts.map((item) => ({
          partId: item.partId,
          quantity: item.quantity,
        })),
      };
      await addOrder(orderRequest);
      fetchOrders();
      setPersonId("");
      setOrderDate("");
      setSelectedParts([]);
    } catch (error) {
      console.error("Error adding order:", error);
    }
  };

  const handleDeleteOrder = async (id) => {
    try {
      await deleteOrder(id);
      fetchOrders();
    } catch (error) {
      console.error("Error deleting order:", error);
    }
  };

  const handleAddPart = (partId) => {
    const part = parts.find((p) => p.id === partId);
    if (part) {
      setSelectedParts([...selectedParts, { partId: part.id, quantity: 1 }]);
    }
  };

  const handleQuantityChange = (partId, quantity) => {
    setSelectedParts(
      selectedParts.map((item) =>
        item.partId === partId ? { ...item, quantity: Number(quantity) } : item
      )
    );
  };

  // Форматирование даты в формат "dd mm yyyy"
  const formatDate = (dateString) => {
    const date = new Date(dateString);
    const day = String(date.getDate()).padStart(2, "0");
    const month = String(date.getMonth() + 1).padStart(2, "0");
    const year = date.getFullYear();
    return `${day} ${month} ${year}`;
  };

  useEffect(() => {
    fetchOrders();
    fetchPersons();
    fetchParts();
  }, []);

  return (
    <div className="container">
      <h1 className="title">Orders</h1>
      <form className="form" onSubmit={(e) => e.preventDefault()}>
        <label>
          Select Person:
          <select
            value={personId}
            onChange={(e) => setPersonId(e.target.value)}
            className="select"
            required
          >
            <option value="" disabled>
              Select Person
            </option>
            {persons.map((person) => (
              <option key={person.id} value={person.id}>
                {person.name} ({person.email})
              </option>
            ))}
          </select>
        </label>

        <label>
          Order Date:
          <input
            type="date"
            value={orderDate}
            onChange={(e) => setOrderDate(e.target.value)}
            className="input"
            required
          />
        </label>

        <label>
          Select Part:
          <select
            onChange={(e) => handleAddPart(Number(e.target.value))}
            className="select"
          >
            <option value="" disabled>
              Select Part
            </option>
            {parts.map((part) => (
              <option key={part.id} value={part.id}>
                {part.name}
              </option>
            ))}
          </select>
        </label>

        <h3>Selected Parts</h3>
        {selectedParts.map((item) => (
          <div key={item.partId} className="listItem">
            <label>
              {parts.find((p) => p.id === item.partId)?.name}:{" "}
              <input
                type="number"
                value={item.quantity}
                onChange={(e) => handleQuantityChange(item.partId, e.target.value)}
                min="1"
                className="input"
                required
              />
            </label>
          </div>
        ))}

        <button onClick={handleAddOrder} className="button">
          Add Order
        </button>
      </form>
      <ul className="list">
        {orders.map((order) => {
          const person = persons.find((p) => p.id === order.personId);
          return (
            <li key={order.id} className="listItem">
              <div>
                <strong>Order ID:</strong> {order.id} <br />
                <strong>Person:</strong> {person ? `${person.name} (${person.email})` : "N/A"} <br />
                <strong>Date:</strong> {formatDate(order.orderDate)}
              </div>
              <div>
                <button
                  onClick={() => handleDeleteOrder(order.id)}
                  className="button"
                  style={{ marginRight: "10px", backgroundColor: "#dc3545" }}
                >
                  Delete
                </button>
                <button onClick={() => handleUpdateOrder(order)} className="button">
                  Update
                </button>
              </div>
            </li>
          );
        })}
      </ul>
    </div>
  );
};

export default OrderPage;
