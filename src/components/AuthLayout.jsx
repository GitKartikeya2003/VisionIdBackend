import bg from "../assets/bg.jpg";

export default function AuthLayout({ children, subtitle }) {

  return (

    <div
      className="h-screen w-full bg-cover bg-center flex items-center justify-center"
      style={{ backgroundImage: `url(${bg})` }}
    >

      <div className="absolute inset-0 bg-black/60"></div>

      <div className="relative bg-gray-900/90 border border-gray-800 rounded-xl p-8 w-96 text-white shadow-lg">

        <h1 className="text-3xl font-bold text-center mb-2">
          VisionID
        </h1>

        <p className="text-gray-400 text-center mb-6">
          {subtitle}
        </p>

        {children}

      </div>

    </div>

  );

}