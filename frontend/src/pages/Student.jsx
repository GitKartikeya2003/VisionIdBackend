import { useState, useContext } from "react";
import Layout from "../components/Layout";
import { AppContext } from "../context/AppContext";
import { Plus, Trash } from "lucide-react";

export default function Student() {

  const { students, setStudents, classes } = useContext(AppContext);

  const [show, setShow] = useState(false);

  const [name, setName] = useState("");
  const [roll, setRoll] = useState("");
  const [classId, setClassId] = useState("");



  const addStudent = () => {

    const newStudent = {
      name,
      roll,
      classId: Number(classId),
    };

    setStudents([...students, newStudent]);

    setShow(false);

    setName("");
    setRoll("");
    setClassId("");

  };


  const deleteStudent = (rollNo) => {

    setStudents(
      students.filter((s) => s.roll !== rollNo)
    );

  };


  return (

    <Layout title="Students">

      {/* top bar */}
      <div className="flex justify-between mb-4">

        <h1 className="text-xl font-bold">
          Students
        </h1>

        <button
          onClick={() => setShow(true)}
          className="bg-blue-600 px-4 py-2 rounded flex gap-2"
        >
          <Plus size={18} />
          Add Student
        </button>

      </div>


      {/* table */}
      <div className="bg-gray-900 rounded overflow-hidden">

        <table className="w-full text-left">

          <thead className="bg-gray-800">

            <tr>

              <th className="p-3">Roll No.</th>
              <th className="p-3">Name</th>
              <th className="p-3">Class</th>
              <th className="p-3">Action</th>

            </tr>

          </thead>

          <tbody>

            {students.map((s) => (

              <tr key={s.roll} className="border-t border-gray-700">

                <td className="p-3">{s.roll}</td>

                <td className="p-3">{s.name}</td>

                <td className="p-3">

                  {
                    classes.find(
                      (c) => c.id === s.classId
                    )?.subject
                  }

                </td>

                <td className="p-3">

                  <button
                    onClick={() => deleteStudent(s.roll)}
                    className="bg-red-600 px-2 py-1 rounded flex gap-1"
                  >
                    <Trash size={14} />
                    Delete
                  </button>

                </td>

              </tr>

            ))}

          </tbody>

        </table>

      </div>



      {/* modal */}
      {show && (

        <div className="fixed inset-0 bg-black/50 flex items-center justify-center">

          <div className="bg-gray-900 p-6 rounded w-96">

            <h2 className="text-lg font-bold mb-4">
              Add Student
            </h2>


            <input
              placeholder="Name"
              className="w-full p-2 mb-2 bg-gray-800"
              value={name}
              onChange={(e) => setName(e.target.value)}
            />

            <input
              placeholder="Roll"
              className="w-full p-2 mb-2 bg-gray-800"
              value={roll}
              onChange={(e) => setRoll(e.target.value)}
            />


            <select
              className="w-full p-2 mb-4 bg-gray-800"
              value={classId}
              onChange={(e) => setClassId(e.target.value)}
            >

              <option value="">
                Select Class
              </option>

              {classes.map((c) => (

                <option key={c.id} value={c.id}>
                  {c.subject}
                </option>

              ))}

            </select>


            <div className="flex justify-end gap-2">

              <button onClick={() => setShow(false)}>
                Cancel
              </button>

              <button
                onClick={addStudent}
                className="bg-blue-600 px-3 py-1 rounded"
              >
                Add
              </button>

            </div>

          </div>

        </div>

      )}

    </Layout>

  );

}