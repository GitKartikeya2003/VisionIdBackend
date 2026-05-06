import { BrowserRouter, Routes, Route } from "react-router-dom";

import Login from "./pages/Login";
import Register from "./pages/Register";
import Dashboard from "./pages/Dashboard";
import Class from "./pages/Class";
import Student from "./pages/Student";

function App() {

  return (

    <BrowserRouter>

      <Routes>

        <Route path="/" element={<Login />} />

        <Route path="/register" element={<Register />} />

        <Route path="/dashboard" element={<Dashboard />} />

        <Route path="/class" element={<Class />} />

        <Route path="/student" element={<Student />} />

      </Routes>

    </BrowserRouter>

  );

}

export default App;