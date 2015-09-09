var a,b,d,i,j:integer;

(*
 * fungsi faktorial
 * param int n
 * return n!
 *)
function fac(n:integer):integer;
begin
	j := 1;
	for i:=2 to n do
	begin
		j := j*i;
	end;
	fac := j;
end;

begin
	// baca nilai a dan b
	readln(a,b); //comment wkwk
	// tulis nilai a + b
	writeln(a+b);
	{
		comment lagi
	}
	d := fac(a);
	writeln(a,' ',d);
end.
