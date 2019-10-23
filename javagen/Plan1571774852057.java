public class Plan1571774852057 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseDimmer("A") ) {
StartServer("B");
} else {
DecreaseTraffic("A");
}

}

StartServer("B");

for (int i = 0; i < 3 ; i++) {
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("B");
}

StartServer("A");

}


StartServer("C");

}
}
