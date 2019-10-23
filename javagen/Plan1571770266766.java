public class Plan1571770266766 extends Plan { 
public static void main(String[] args) { 
if ( StartServer("A") ) {
StartServer("B");
} else {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

}

StartServer("A");

if ( DecreaseTraffic("C") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

StartServer("B");
for (int i = 0; i < 4 ; i++) {
if ( StartServer("A") ) {
StartServer("B");
} else {
StartServer("A");
}

}



StartServer("B");

} else {
if ( StartServer("A") ) {
StartServer("A");
} else {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

}

StartServer("A");

StartServer("B");

}


}
}
