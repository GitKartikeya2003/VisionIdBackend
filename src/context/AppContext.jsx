import { createContext, useState } from "react";

export const AppContext = createContext();

export default function AppProvider({ children }) {

  const [classes, setClasses] = useState([
    {
      id: 1,
      subject: "Machine Learning",
      teacher: "Dr. Sharma",
      time: "08:00",
      venue: "CR-8",
    },
    {
      id: 2,
      subject: "Computer Networks",
      teacher: "Dr. Singh",
      time: "10:00",
      venue: "CR-4",
    },
    {
      id: 3,
      subject: "DBMS",
      teacher: "Dr. Rao",
      time: "12:00",
      venue: "Lab-2",
    },
  ]);

  const [students, setStudents] = useState([
    { roll: "23A01", name: "Rahul", classId: 1 },
    { roll: "23A02", name: "Aman", classId: 1 },
    { roll: "23A03", name: "Riya", classId: 2 },
  ]);

  const [selectedClass, setSelectedClass] = useState(null);

  return (

    <AppContext.Provider
      value={{
        classes,
        setClasses,
        students,
        setStudents,
        selectedClass,
        setSelectedClass,
      }}
    >

      {children}

    </AppContext.Provider>

  );
}