client
--------------------------------------------------
Compilation and execution with the FraSCAti script:
---------------------------------------------------
frascati compile src client
frascati run client -libpath client.jar -s r -m run

para correr el cliente se debe tener al mismo nivel en jerarquia al jar una
carpeta llama data con el archivo test.txt

Ahi mismo se encontrara el resultado del test.

example:

|_client.jar
|_Data
  |_test.txt
