import { useState, useContext } from "react";
import Layout from "../components/Layout";
import { AppContext } from "../context/AppContext";
import { Plus } from "lucide-react";

export default function Class() {

  const { classes, setClasses } = useContext(AppContext);

  const [show, setShow] = useState(false);

  const [subject, setSubject] = useState("");
  const [teacher, setTeacher] = useState("");
  const [time, setTime] = useState("");
  const [venue, setVenue] = useState("");



  const addClass = () => {

    const newClass = {
      id: Date.now(),
      subject,
      teacher,
      time,
      venue,
    };

    setClasses([...classes, newClass]);

    setShow(false);

    setSubject("");
    setTeacher("");
    setTime("");
    setVenue("");

  };


  return (

    <Layout title="Classes">

      {/* top bar */}
      <div className="flex justify-between mb-6">

        <h1 className="text-2xl font-bold">
          Classes
        </h1>

        <button
          onClick={() => setShow(true)}
          className="bg-blue-600 px-4 py-2 rounded flex gap-2"
        >
          <Plus size={18} />
          Add Class
        </button>

      </div>



      {/* class cards */}
      <div className="grid grid-cols-3 gap-6">

        {classes.map((c) => (

          <div
            key={c.id}
            className="bg-gray-900 border border-gray-800 rounded-xl p-4 shadow hover:bg-gray-800 transition"
          >

            <h2 className="text-lg font-bold mb-2">
              {c.subject}
            </h2>

            <p className="text-gray-400">
              Teacher: {c.teacher}
            </p>

            <p className="text-gray-400">
              Time: {c.time}
            </p>

            <p className="text-gray-400">
              Venue: {c.venue}
            </p>

          </div>

        ))}

      </div>



      {/* modal */}
      {show && (

        <div className="fixed inset-0 bg-black/50 flex items-center justify-center">

          <div className="bg-gray-900 p-6 rounded w-96">

            <h2 className="text-lg font-bold mb-4">
              Add Class
            </h2>

            <input
              placeholder="Subject"
              className="w-full p-2 mb-2 bg-gray-800"
              value={subject}
              onChange={(e) => setSubject(e.target.value)}
            />

            <input
              placeholder="Teacher"
              className="w-full p-2 mb-2 bg-gray-800"
              value={teacher}
              onChange={(e) => setTeacher(e.target.value)}
            />

            <input
              placeholder="Time"
              className="w-full p-2 mb-2 bg-gray-800"
              value={time}
              onChange={(e) => setTime(e.target.value)}
            />

            <input
              placeholder="Venue"
              className="w-full p-2 mb-4 bg-gray-800"
              value={venue}
              onChange={(e) => setVenue(e.target.value)}
            />

            <div className="flex justify-end gap-2">

              <button onClick={() => setShow(false)}>
                Cancel
              </button>

              <button
                onClick={addClass}
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