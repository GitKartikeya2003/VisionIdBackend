import { useNavigate, useLocation } from "react-router-dom";
import {
  LayoutDashboard,
  Users,
  School,
  LogOut
} from "lucide-react";

export default function Layout({ children, title }) {

  const nav = useNavigate();
  const location = useLocation();

  const menu = [
    { name: "Dashboard", path: "/dashboard", icon: LayoutDashboard },
    { name: "Classes", path: "/class", icon: School },
    { name: "Students", path: "/student", icon: Users },
  ];

  return (

    <div className="flex h-screen bg-gray-950 text-white">

      {/* Sidebar */}
      <div className="w-64 bg-gray-900 flex flex-col border-r border-gray-800">

        <h1 className="text-2xl font-bold p-4 border-b border-gray-800">
          VisionID
        </h1>

        <div className="flex-1">

          {menu.map((item) => {

            const Icon = item.icon;
            const active = location.pathname === item.path;

            return (

              <button
                key={item.name}
                onClick={() => nav(item.path)}
                className={`flex items-center gap-2 w-full p-3 text-left transition
                ${active
                    ? "bg-gray-800 text-white"
                    : "hover:bg-gray-800 text-gray-300"}
                `}
              >

                <Icon size={18} />

                {item.name}

              </button>

            );

          })}

        </div>


        {/* Logout */}
        <button
          onClick={() => nav("/")}
          className="p-3 bg-red-600 hover:bg-red-700 flex items-center gap-2"
        >
          <LogOut size={18} />
          Logout
        </button>

      </div>



      {/* Right side */}
      <div className="flex-1 flex flex-col">

        {/* Navbar */}
        <div className="h-14 bg-gray-900 border-b border-gray-800 flex items-center px-4">

          <h2 className="font-semibold text-lg">
            {title}
          </h2>

        </div>


        {/* Content */}
        <div className="flex-1 bg-gray-950 p-6">

          {children}

        </div>

      </div>

    </div>

  );
}