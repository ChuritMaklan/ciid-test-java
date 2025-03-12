import React, { useState, useEffect } from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import { updateOrder, getOrderById } from "../../../services/OrderService";
import { getPersons } from "../../../services/PersonService";
import { getParts } from "../../../services/PartService";

const OrderUpdatePage = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const { id } = useParams();

  const [persons, setPersons] = useState([]);
  const [parts, setParts] = useState([]);
  const [personId, setPersonId] = useState("");
  const [orderDate, setOrderDate] = useState("");
  const [orderItems, setOrderItems] = useState([]);

  useEffect(() => {
    if (location.state?.order) {
      const { orderDate, personId, orderItems } = location.state.order;
      setPersonId(personId);
      setOrderDate(orderDate);
      setOrderItems(orderItems);
    }

    fetchPersons();
    fetchParts();
  }, [location.state]);

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

  const handleAddPart = (partId) => {
    const part = parts.find((p) => p.id === partId);
    if (part && !orderItems.some((item) => item.partId === partId)) {
      setOrderItems([...orderItems, { partId: part.id, quantity: 1 }]);
    }
  };

  const handleQuantityChange = (partId, quantity) => {
    setOrderItems(
      orderItems.map((item) =>
        item.partId === partId ? { ...item, quantity: Number(quantity) } : item
      )
    );
  };

  const handleRemovePart = (partId) => {
    setOrderItems(orderItems.filter((item) => item.partId !== partId));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const orderData = {
        id: Number(id),
        personId: Number(personId),
        orderDate: new Date(orderDate).toISOString(),
        orderItems: orderItems.map((item) => ({
          partId: item.partId,
          quantity: item.quantity,
        })),
      };
      await updateOrder(orderData);
      navigate("/orders");
    } catch (error) {
      console.error("Error updating order:", error);
    }
  };

  return (
    <div className="container">
      <h1 className="title">Update Order</h1>
      <form className="form" onSubmit={handleSubmit}>
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
                {person.name}
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
        {orderItems.map((item) => (
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
            <button
              type="button"
              onClick={() => handleRemovePart(item.partId)}
              className="button"
              style={{ backgroundColor: "#dc3545" }}
            >
              Remove
            </button>
          </div>
        ))}

        <button type="submit" className="button">
          Update Order
        </button>
      </form>
    </div>
  );
};

export default OrderUpdatePage;
