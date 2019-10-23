public class Plan1571769335360 extends Plan { 
public static void main(String[] args) { 
if (  ) {
StartServer("C");
} else {
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
}


}

for (int i = 0; i < 2 ; i++) {
if ( DecreaseDimmer("C") ) {
ShutdownServer("A");
} else {
StartServer("B");
}

}


StartServer("B");
StartServer("B");
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}




}
}
