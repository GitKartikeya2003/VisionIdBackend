import { useContext } from "react";
import Layout from "../components/Layout";
import { AppContext } from "../context/AppContext";

import {
  Users,
  School,
  ClipboardCheck,
  Activity
} from "lucide-react";

export default function Dashboard() {

  const { classes, students } = useContext(AppContext);

  const cards = [

    {
      title: "Total Students",
      value: students.length,
      icon: Users,
      color: "bg-blue-600"
    },

    {
      title: "Active Classes",
      value: classes.length,
      icon: School,
      color: "bg-green-600"
    },

    {
      title: "Attendance",
      value: "92%",
      icon: ClipboardCheck,
      color: "bg-purple-600"
    },

    {
      title: "Recent Activity",
      value: "5 updates",
      icon: Activity,
      color: "bg-orange-600"
    }

  ];

  return (

    <Layout title="Dashboard">

      <div className="min-h-full bg-gray-900 text-white p-6 rounded-lg">

        <h1 className="text-2xl font-bold mb-6">
          Teacher Dashboard
        </h1>

        <div className="grid grid-cols-4 gap-6">

          {cards.map((card) => {

            const Icon = card.icon;

            return (

              <div
                key={card.title}
                className="bg-gray-800 rounded-xl p-5 flex justify-between"
              >

                <div>

                  <p className="text-gray-400">
                    {card.title}
                  </p>

                  <h2 className="text-2xl font-bold">
                    {card.value}
                  </h2>

                </div>

                <div className={`p-3 rounded ${card.color}`}>
                  <Icon size={22} />
                </div>

              </div>

            );

          })}

        </div>

      </div>

    </Layout>

  );

}