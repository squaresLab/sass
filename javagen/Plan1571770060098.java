public class Plan1571770060098 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}


if ( StartServer("C") ) {
if ( StartServer("A") ) {
StartServer("A");
} else {
StartServer("B");
}

} else {
StartServer("C");
}


}

}
}
