var a,b,d:integer;

(*
 * fungsi faktorial
 * param int n
 * return n!
 *)
function fac(n:integer):integer;
begin
	if (n=0) then
	begin
		fac := 1;
	end else
	begin
		fac := n*fac(n-1);
	end;
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
