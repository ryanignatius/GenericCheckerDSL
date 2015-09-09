var a,b,d:integer;

(*
 * fungsi faktorial
 * param int n
 * return n!
 *)
function facs(n:integer):integer;
begin
	if (n=0) then
	begin
		facs := 1;
	end else
	begin
		facs := n*facs(n-1);
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
	d := facs(a);
	writeln(a,' ',d);
end.
