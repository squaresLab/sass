public class Plan1571768004081 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( StartServer("C") ) {
StartServer("C");
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}


if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

} else {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

}


} else {
StartServer("C");
}

}

}
}
