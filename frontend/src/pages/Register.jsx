import { useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthLayout from "../components/AuthLayout";

export default function Register() {

  const nav = useNavigate();

  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const register = () => {

    nav("/");

  };

  return (

    <AuthLayout subtitle="Create Account">

      <input
        placeholder="Name"
        className="w-full p-2 mb-2 bg-gray-800 rounded"
        value={name}
        onChange={(e) => setName(e.target.value)}
      />

      <input
        placeholder="Email"
        className="w-full p-2 mb-2 bg-gray-800 rounded"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />

      <input
        type="password"
        placeholder="Password"
        className="w-full p-2 mb-4 bg-gray-800 rounded"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />

      <button
        onClick={register}
        className="w-full bg-green-600 p-2 rounded"
      >
        Register
      </button>

      <p className="text-sm text-center mt-4">

        Already have account?{" "}

        <span
          className="text-blue-400 cursor-pointer"
          onClick={() => nav("/")}
        >
          Login
        </span>

      </p>

    </AuthLayout>

  );

}