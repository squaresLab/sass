public class Plan1571769026593 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("C");
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}


if ( StartServer("C") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {
StartServer("C");
}

for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
}



}

}
}
