import { useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthLayout from "../components/AuthLayout";

import { Eye, EyeOff } from "lucide-react";

export default function Login() {

  const nav = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const [show, setShow] = useState(false);

  const login = () => {
    nav("/dashboard");
  };

  return (

    <AuthLayout subtitle="Teacher Login">

      <input
        placeholder="Email"
        className="w-full p-2 mb-3 bg-gray-800 rounded"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />

      {/* password with eye */}
      <div className="relative mb-4">

        <input
          type={show ? "text" : "password"}
          placeholder="Password"
          className="w-full p-2 bg-gray-800 rounded"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />

        <div
          className="absolute right-2 top-2 cursor-pointer"
          onClick={() => setShow(!show)}
        >
          {show ? <EyeOff size={18} /> : <Eye size={18} />}
        </div>

      </div>

      <button
        onClick={login}
        className="w-full bg-blue-600 p-2 rounded"
      >
        Login
      </button>

      <p className="text-sm text-center mt-4">

        Don't have account?{" "}

        <span
          className="text-blue-400 cursor-pointer"
          onClick={() => nav("/register")}
        >
          Register
        </span>

      </p>

    </AuthLayout>

  );

}