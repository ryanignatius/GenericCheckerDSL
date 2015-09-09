var a,b,d,i:integer;

begin
	// baca nilai a dan b
	readln(a,b); //comment wkwk
	// tulis nilai a + b
	writeln(a+b);
	{
		comment lagi
	}
	d := 1;
	for i:=2 to a do
	begin
		d := d*i;
	end;
	writeln(a,' ',d);
end.
